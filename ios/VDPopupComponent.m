//
//  VDPopupComponent.m
//  Pods
//
//  Created by XHY on 2017/4/26.
//
//

#import "VDPopupComponent.h"
#import "VDMaskComponent.h"
#import <WeexPluginLoader/WeexPluginLoader.h>

@interface VDPopupComponent ()

@property (nonatomic) CGFloat rowHeight;
@property (nonatomic) BOOL needUpdateFrame; /**< 兼容一些弹窗问题 */

@end

@implementation VDPopupComponent
WX_PlUGIN_EXPORT_COMPONENT(vdpop, VDPopupComponent)

- (instancetype)initWithRef:(NSString *)ref type:(NSString *)type styles:(NSDictionary *)styles attributes:(NSDictionary *)attributes events:(NSArray *)events weexInstance:(WXSDKInstance *)weexInstance
{
    if (self = [super initWithRef:ref type:type styles:styles attributes:attributes events:events weexInstance:weexInstance]) {
        if (attributes[@"updatePosition"]) {
            _needUpdateFrame = [WXConvert BOOL:attributes[@"updatePosition"]];
        }
    }
    return self;
}

- (UIView *)loadView
{
    UIButton *btn = [[UIButton alloc] init];
    return btn;//[[UIView alloc] init];
}

- (void)viewDidLoad
{
    [super viewDidLoad];
}

- (void)layoutDidFinish
{
    [super layoutDidFinish];
    
    if (_needUpdateFrame && [self.supercomponent isKindOfClass:[VDMaskComponent class]]) {
        [(VDMaskComponent *)self.supercomponent getPopViewRectNeedUpdateFrame:YES];
    }
    
}


@end
